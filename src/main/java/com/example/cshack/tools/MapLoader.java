package com.example.cshack.tools;

import com.example.cshack.model.FieldType;
import com.example.cshack.model.MazeMap;
import com.example.cshack.model.MazeMapImpl;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Service
public class MapLoader {



    public MazeMap Load(String name) {
        MazeMap map = new MazeMapImpl();

        readFile(name, (line, i) -> {
            for(int j = 0; j < line.length(); j++) {
                FieldType t = FieldType.Unknown;
                switch (line.charAt(j)) {
                    case '#': t = FieldType.Wall; break;
                    case ' ': t = FieldType.Floor; break;
                    case 'A': t = FieldType.Start; break;
                    case 'B': t = FieldType.Finish; break;
                    case '?': t = FieldType.Unknown; break;
                }
                map.mark(i, j, t);
            }
        });

        return map;
    }

    private void readFile(String fileName, BiConsumer<String, Integer> lineConsumer) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineConsumer.accept(line, i);
                i++;
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

