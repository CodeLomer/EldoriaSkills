package com.github.codelomer.eldoriaskills.task;

import com.github.codelomer.eldoriaskills.api.EldoriaPlayer;
import com.github.codelomer.eldoriaskills.registry.EldoriaPlayerRegistry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EldoriaPlayerSaveTask implements Runnable{

    private final EldoriaPlayerRegistry playerRegistry;

    @Override
    public void run() {
        for(EldoriaPlayer player: playerRegistry.getOnlinePlayers()){
            
        }
    }
}
