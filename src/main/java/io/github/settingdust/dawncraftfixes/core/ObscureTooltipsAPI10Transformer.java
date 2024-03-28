package io.github.settingdust.dawncraftfixes.core;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.commons.MethodRemapper;
import org.objectweb.asm.commons.SimpleRemapper;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObscureTooltipsAPI10Transformer implements ITransformer<MethodNode> {
    private static final Map<String, String> REMAPPER = new HashMap<>() {
        {
            put("com/obscuria/obscureapi/ObscureAPI.addMod(Ljava/lang/String;Ljava/lang/String;)V", "collectionMod");
            put("com/obscuria/obscureapi/classes/IClassItem", "com/obscuria/obscureapi/world/classes/IClassItem");
            put("com/obscuria/obscureapi/classes/ObscureClass", "com/obscuria/obscureapi/world/classes/ObscureClass");
        }
    };

    @Override
    public @NotNull MethodNode transform(final MethodNode input, final ITransformerVotingContext context) {
        if (input.name.equals("drawTooltip")) {
            for (final AbstractInsnNode instruction : input.instructions) {
                if (instruction instanceof MethodInsnNode methodInsnNode
                        && methodInsnNode.owner.equals("com/obscuria/obscureapi/utils/TextHelper")
                        && methodInsnNode.name.equals("component")) {
                    methodInsnNode.desc = "(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;";
                }
            }
        }
        var remapper = new SimpleRemapper(REMAPPER);
        var node = new MethodNode(
                input.access, input.name, input.desc, input.signature, input.exceptions.toArray(String[]::new));
        var methodRemapper = new MethodRemapper(node, remapper);
        input.accept(methodRemapper);
        return node;
    }

    @Override
    public @NotNull TransformerVoteResult castVote(final ITransformerVotingContext context) {
        return TransformerVoteResult.YES;
    }

    @Override
    public @NotNull Set<Target> targets() {
        return Set.of(
                Target.targetMethod("com.obscuria.obscuretooltips.ObscureTooltipsMod", "<init>", "()V"),
                Target.targetMethod(
                        "com.obscuria.obscuretooltips.Resources",
                        "getType",
                        "(Lnet/minecraft/world/item/ItemStack;Lcom/obscuria/obscuretooltips/tooltips/Override;)Ljava/lang/String;"),
                Target.targetMethod(
                        "com/obscuria/obscuretooltips/tooltips/TooltipRenderer",
                        "drawTooltip",
                        "(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/util/List;Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V"));
    }
}
