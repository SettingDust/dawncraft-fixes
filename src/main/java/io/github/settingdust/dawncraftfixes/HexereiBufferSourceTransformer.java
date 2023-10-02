package io.github.settingdust.dawncraftfixes;

import com.mojang.logging.LogUtils;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.slf4j.Logger;

import java.util.Map;
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
                "drawEntity",
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
                    for (AbstractInsnNode instruction : method.instructions) {
                        if (instruction instanceof TypeInsnNode typeInsnNode
                                && typeInsnNode.getOpcode() == Opcodes.CHECKCAST
                                && typeInsnNode.desc.equals(
                                        "net/minecraft/client/renderer/MultiBufferSource$BufferSource")) {
                            method.instructions.remove(instruction);
                            LOGGER.debug("Removed CHECKCAST of {} in {}#{}", typeInsnNode.desc, name, method.name);
                        }
                    }

                    for (AbstractInsnNode instruction : method.instructions) {
                        if (instruction instanceof MethodInsnNode methodInsnNode) {
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
                            if (methodInsnNode.owner.equals("net/minecraft/client/renderer/MultiBufferSource$BufferSource")) {
                                methodInsnNode.owner = "net/minecraft/client/renderer/MultiBufferSource";
                            }
                        }
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
                    if (instruction instanceof MethodInsnNode methodInsnNode) {
                        if (methods.contains(methodInsnNode.name)) {
                            methodInsnNode.desc = methodInsnNode.desc.replace(
                                    "net/minecraft/client/renderer/MultiBufferSource$BufferSource",
                                    "net/minecraft/client/renderer/MultiBufferSource");
                            LOGGER.debug(
                                    "Replaced MultiBufferSource$BufferSource to MultiBufferSource for {}#{}{}",
                                    name,
                                    method.name,
                                    methodInsnNode.desc);
                        }
                        if (methodInsnNode.owner.equals("net/minecraft/client/renderer/MultiBufferSource$BufferSource")) {
                            methodInsnNode.owner = "net/minecraft/client/renderer/MultiBufferSource";
                        }
                    }
                }
            }
        }
        return input;
    }

    @Override
    public @NotNull TransformerVoteResult castVote(ITransformerVotingContext context) {
        return TransformerVoteResult.YES;
    }

    @Override
    public @NotNull Set<Target> targets() {
        return Set.of(
                Target.targetClass("net.joefoxe.hexerei.data.books.PageDrawing"),
                Target.targetClass("net.joefoxe.hexerei.item.custom.HexereiBookItemRenderer"));
    }
}
