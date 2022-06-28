package Model;

import java.io.*;
import java.util.Scanner;

public class EstadoModel {
    File file;

    public EstadoModel() {
        this.file = new File("C:\\Users\\Vitor\\IdeaProjects\\SegundaAtividadePratica\\src\\Data\\estados.txt");
    }

    public void printAllData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            while ((bufferedReader.readLine()) != null){
                System.out.println(bufferedReader.readLine());
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("IO Exception.");
        }
    }

    public String findAll() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append("|");
            }
            bufferedReader.close();

            return stringBuilder.toString();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("IO Exception.");
        }
        finally {
            return stringBuilder.toString();
        }
    }
}
