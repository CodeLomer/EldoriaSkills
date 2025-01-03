package com.github.codelomer.eldoriaskills.util;

import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorUtilities {
    private ColorUtilities(){}
    
    public static String toColorText(String from) {
        if(from == null) return null;
        Pattern pattern = Pattern.compile("&#[a-fA-F\0-9]{6}");
        Matcher matcher = pattern.matcher(from);
        while (matcher.find()) {
            String hexCode = from.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace("&#", "x");
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch)
                builder.append("&").append(c);
            from = from.replace(hexCode, builder.toString());
            matcher = pattern.matcher(from);
        }
        return ChatColor.translateAlternateColorCodes('&', from);
    }

    public static List<String> toColorText(List<String> text) {
        if(text == null) return Collections.emptyList();
        return text.stream().filter(Objects::nonNull).map(ColorUtilities::toColorText).toList();
    }
}
