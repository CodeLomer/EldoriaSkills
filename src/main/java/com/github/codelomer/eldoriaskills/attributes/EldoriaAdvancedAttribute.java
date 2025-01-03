package com.github.codelomer.eldoriaskills.attributes;

import com.github.codelomer.eldoriaskills.api.EldoriaAttribute;
import com.github.codelomer.eldoriaskills.api.Updatable;
import lombok.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class EldoriaAdvancedAttribute implements EldoriaAttribute, Updatable {
    private final @NonNull String id;
    private final @NonNull UUID ownerUuid;
    private String attributeName;
    @Getter
    @Setter
    private @NonNull String formula;
    @Override
    public @NonNull UUID getOwnerUuid() {
        return ownerUuid;
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public @NonNull String getId() {
        return id;
    }

    @Override
    public void update() {
        Player player = Bukkit.getPlayer(ownerUuid);
        if(player != null){
            String filledFormula = PlaceholderAPI.setPlaceholders(player,formula);
            updateValue(new ExpressionBuilder(filledFormula).build().evaluate());
        }
    }

    protected abstract void updateValue(double value);
}
