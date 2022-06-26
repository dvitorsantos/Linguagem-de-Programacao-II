package Controller;

import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;
import Model.EstadoModel;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainController {
    EstadoModel estadoModel = new EstadoModel();

    public ArrayList<Pais> carregarArquivo(ArrayList<Pais> paises) {
        String data = this.estadoModel.findAll();

        StringTokenizer estadosString = new StringTokenizer(data, "|");

        System.out.println(data);

        RegiaoController regiaoController = new RegiaoController();
        PaisController paisController = new PaisController();
        EstadoController estadoController = new EstadoController();

        while (estadosString.hasMoreElements()) {
            StringTokenizer info = new StringTokenizer((String) estadosString.nextElement(), ";");

            String nome = info.nextElement().toString();
            String sigla = info.nextElement().toString().replaceAll("\\s","");
            String capital = info.nextElement().toString();
            String nomeRegiao =  info.nextElement().toString().replaceAll("\\s","");
            double area = Double.parseDouble(info.nextElement().toString().replaceAll("\\s",""));
            int populacao = Integer.parseInt(info.nextElement().toString().replaceAll("\\s",""));
            float pib = Float.parseFloat(info.nextElement().toString().replaceAll("\\s",""));
            float idh = Float.parseFloat(info.nextElement().toString().replaceAll("\\s",""));

            Regiao regiao = regiaoController.getByName(paises, nomeRegiao);

            if (regiao == null) {
                regiao = new Regiao(nomeRegiao, nomeRegiao);

                Pais pais = paisController.get(paises, "BRA");
                if (pais == null) {
                    pais = new Pais("Brasil", "BRA");
                    paises = paisController.save(paises, pais);
                }

                paises = regiaoController.save(paises, regiao, "BRA");
            }

            Estado estado = new Estado(nome, sigla, capital, area, populacao, pib, idh);

            paises = estadoController.save(paises, estado, "BRA", nomeRegiao);
        }

        return paises;
    }
}
