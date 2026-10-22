package com.no_stack_limit;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class NoStackLimitClient implements ClientModInitializer {
    private static KeyMapping showExactCountKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath(NoStackLimit.MOD_ID, "controls"));
        showExactCountKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.no_stack_limit.show_exact_count",
                InputConstants.KEY_LALT,
                category));
        ItemTooltipCallback.EVENT.register((stack, context, type, lines) ->
                TooltipHelper.appendExactCount(lines, stack));
    }

    public static boolean shouldShowExactCount() {
        return showExactCountKey != null && showExactCountKey.isDown();
    }
}
