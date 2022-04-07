package fr.deride.sbtreasuremap;

import fr.deride.sbtreasuremap.domain.Adventurer;
import fr.deride.sbtreasuremap.domain.Map;
import fr.deride.sbtreasuremap.domain.Mountain;
import fr.deride.sbtreasuremap.domain.Treasure;
import fr.deride.sbtreasuremap.services.EntryFileInput;
import fr.deride.sbtreasuremap.services.EntryFileOutput;
import fr.deride.sbtreasuremap.services.MapCreation;
import fr.deride.sbtreasuremap.services.SimulateRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SbTreasureMapApplication {


    private final EntryFileInput           entryFileInput =  new EntryFileInput();
    private final EntryFileOutput          entryFileOutput = new EntryFileOutput();
    private final MapCreation              mapCreation = new MapCreation();
    private final SimulateRoute            simulateRoute = new SimulateRoute();
    static        SbTreasureMapApplication instance       = new SbTreasureMapApplication();



    public static void main(String[] args) throws IOException {
        SpringApplication.run(SbTreasureMapApplication.class, args);
        instance.run();
    }

    public void run() {
        List<String> lines = EntryFileInput.readFile();
        Map mapLine = EntryFileInput.getMap(lines);
        List<Mountain> mountainLines = EntryFileInput.getMountains(lines);
        List<Treasure> treasures = EntryFileInput.getTreasures(lines);
        List<Adventurer> adventurers = EntryFileInput.getAdventurers(lines);
        this.mapCreation.createMap(mapLine,mountainLines, treasures, adventurers);
        List<String> outputLine = this.simulateRoute.run(mapLine,mountainLines,treasures,adventurers);
        this.entryFileOutput.createFile();
        this.entryFileOutput.writeOnFile(outputLine);
        this.mapCreation.displayMapOutput(outputLine);
    }

}
