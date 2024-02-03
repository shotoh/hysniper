package io.github.shotoh.hysniper.mixins;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.shotoh.hysniper.core.HySniperConfig;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {
    private static final String LOBBY = "mWYSI";

    @Inject(method = "renderScoreboard", at = @At("HEAD"))
    public void renderScoreboard(ScoreObjective objective, ScaledResolution scaledResolution, CallbackInfo ci) {
        if (!HySniperConfig.lobbyHider) return;
        Scoreboard scoreboard = objective.getScoreboard();
        if (scoreboard == null || objective != scoreboard.getObjectiveInDisplaySlot(1)) return;
        for (Score score : scoreboard.getSortedScores(objective).stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .collect(Collectors.toList())) {
            String scorePlayerName = score.getPlayerName();
            ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(scorePlayerName);
            if (scorePlayerName == null || scorePlayerTeam == null) continue;
            String scoreName = ScorePlayerTeam.formatPlayerName(scorePlayerTeam, scorePlayerName);
            Matcher matcher = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-9][0-9]").matcher(scoreName);
            if (matcher.find()) {
                scorePlayerTeam.setNamePrefix(ChatFormatting.GRAY + matcher.group() + ChatFormatting.DARK_GRAY + LOBBY);
                scorePlayerTeam.setTeamName("");
                scorePlayerTeam.setNameSuffix("");
            }
        }
    }
}
