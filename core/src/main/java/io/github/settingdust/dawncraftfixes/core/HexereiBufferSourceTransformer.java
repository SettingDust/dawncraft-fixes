package io.github.settingdust.dawncraftfixes.core;

import com.mojang.logging.LogUtils;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.CheckClassAdapter;
import org.slf4j.Logger;

import java.util.Set;

/**
 * <a href="https://github.com/JoeFoxe/Hexerei-1.19/pull/38/files">...</a>
 */
public class HexereiBufferSourceTransformer implements ITransformer<ClassNode> {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public @NotNull ClassNode transform(ClassNode input, ITransformerVotingContext context) {
        String name = input.name;
        final var methods = Set.of(
                "renderItem",
                "drawPage",
                "drawLivingEntity",
                "lambda$drawLivingEntity$4",
                "drawEntity",
                "lambda$drawEntity$5",
                "drawPages",
                "drawBaseButtons",
                "drawItemInSlot",
                "drawSlot",
                "drawFluidInSlot",
                "drawFluid",
                "drawTiledSprite",
                "drawTextureWithMasking",
                "drawTooltipImage",
                "drawTooltipText",
                "drawBookmark",
                "drawImage",
                "drawTitle",
                "drawCharacter",
                "drawString");
        if (name.equals("net/joefoxe/hexerei/item/custom/HexereiBookItemRenderer")) {
            for (int i = 0; i < input.methods.size(); i++) {
                final var method = input.methods.get(i);
                if (method.name.equals("renderTileStuff")) {
                    replaceCasts(method, name);

                    for (AbstractInsnNode instruction : method.instructions) {
                        replaceMethodInsnNode(method, instruction, methods, name);
                    }
                }
            }
        }

        if (name.equals("net/joefoxe/hexerei/data/books/PageDrawing")) {
            for (int i = 0; i < input.methods.size(); i++) {
                final var method = input.methods.get(i);
                method.desc = method.desc.replace(
                        "net/minecraft/client/renderer/MultiBufferSource$BufferSource",
                        "net/minecraft/client/renderer/MultiBufferSource");

                for (LocalVariableNode localVariable : method.localVariables) {
                    if (localVariable.desc.equals("Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;")) {
                        localVariable.desc = "Lnet/minecraft/client/renderer/MultiBufferSource;";

                        LOGGER.debug(
                                "Replaced MultiBufferSource$BufferSource to MultiBufferSource for {} {} of {}#{}",
                                localVariable.name,
                                localVariable.desc,
                                name,
                                method.name);
                    }
                }

                for (AbstractInsnNode instruction : method.instructions) {
                    replaceMethodInsnNode(method, instruction, methods, name);
                    replaceLambdaCalls(instruction, methods);
                }
            }

            ClassWriter writer = new ClassWriter(0);
            input.accept(writer);
            writer.visitSource(".transformer.out" + name + ".class", null);
        }

        if (name.equals("net/joefoxe/hexerei/tileentity/renderer/BookOfShadowsAltarRenderer")) {
            for (MethodNode method : input.methods) {
                if (method.name.equals("render")) {
                    replaceCasts(method, name);

                    for (AbstractInsnNode instruction : method.instructions) {
                        replaceMethodInsnNode(method, instruction, methods, name);
                    }
                }
            }
        }

        CheckClassAdapter checkClassAdapter = new CheckClassAdapter(null);
        input.accept(checkClassAdapter);

        return input;
    }

    private static void replaceLambdaCalls(AbstractInsnNode instruction, Set<String> methods) {
        if (instruction instanceof InvokeDynamicInsnNode dynamicInsnNode) {
            if (dynamicInsnNode.desc.contains("MultiBufferSource$BufferSource")) {
                dynamicInsnNode.desc =
                        dynamicInsnNode.desc.replace("MultiBufferSource$BufferSource", "MultiBufferSource");
                Handle bsm = dynamicInsnNode.bsm;
                if (bsm.getOwner().equals("java/lang/invoke/LambdaMetafactory")
                        && dynamicInsnNode.bsmArgs[1] instanceof Handle lambdaHandle
                        && methods.contains(lambdaHandle.getName())) {
                    dynamicInsnNode.bsmArgs[1] = new Handle(
                            lambdaHandle.getTag(),
                            lambdaHandle.getOwner(),
                            lambdaHandle.getName(),
                            lambdaHandle
                                    .getDesc()
                                    .replace("MultiBufferSource$BufferSource", "MultiBufferSource"),
                            lambdaHandle.isInterface());
                }
            }
        }
    }

    private static void replaceMethodInsnNode(
            MethodNode method, AbstractInsnNode instruction, Set<String> methods, String name) {
        if (instruction instanceof MethodInsnNode methodInsnNode) {
            replaceMethodsDesc(methodInsnNode, methods, name, method);
            replaceMethodsCalls(methodInsnNode);
        }
    }

    private static void replaceCasts(MethodNode method, String name) {
        for (AbstractInsnNode instruction : method.instructions) {
            if (instruction instanceof TypeInsnNode typeInsnNode
                    && typeInsnNode.getOpcode() == Opcodes.CHECKCAST
                    && typeInsnNode.desc.equals("net/minecraft/client/renderer/MultiBufferSource$BufferSource")) {
                method.instructions.remove(instruction);
                LOGGER.debug("Removed CHECKCAST of {} in {}#{}", typeInsnNode.desc, name, method.name);
            }
        }
    }

    private static void replaceMethodsDesc(
            MethodInsnNode methodInsnNode, Set<String> methods, String name, MethodNode method) {
        if (methods.contains(methodInsnNode.name)) {
            methodInsnNode.desc = methodInsnNode.desc.replace(
                    "net/minecraft/client/renderer/MultiBufferSource$BufferSource",
                    "net/minecraft/client/renderer/MultiBufferSource");
            LOGGER.debug(
                    "Replaced MultiBufferSource$BufferSource to MultiBufferSource for {}#{} {}{}",
                    name,
                    method.name,
                    methodInsnNode.name,
                    methodInsnNode.desc);
        }
    }

    private static void replaceMethodsCalls(MethodInsnNode methodInsnNode) {
        if (methodInsnNode.owner.equals("net/minecraft/client/renderer/MultiBufferSource$BufferSource")
                && methodInsnNode.name.equals("m_6299_")) {
            methodInsnNode.setOpcode(Opcodes.INVOKEINTERFACE);
            methodInsnNode.owner = "net/minecraft/client/renderer/MultiBufferSource";
            methodInsnNode.itf = true;
        }
    }

    @Override
    public @NotNull TransformerVoteResult castVote(ITransformerVotingContext context) {
        return TransformerVoteResult.YES;
    }

    @Override
    public @NotNull Set<Target> targets() {
        return Set.of(
                Target.targetClass("net.joefoxe.hexerei.data.books.PageDrawing"),
                Target.targetClass("net.joefoxe.hexerei.item.custom.HexereiBookItemRenderer"),
                Target.targetClass("net.joefoxe.hexerei.tileentity.renderer.BookOfShadowsAltarRenderer"));
    }
}
