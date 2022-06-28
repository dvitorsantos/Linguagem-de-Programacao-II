package Database;

import Controller.EstadoController;
import Controller.PaisController;
import Controller.RegiaoController;
import Entity.Estado;
import Entity.Pais;
import Entity.Regiao;
import Entity.Territorio;

import java.util.ArrayList;

public class Database {
    private ArrayList <Pais> paises;

    private EstadoController estadoController;
    private RegiaoController regiaoController;
    private PaisController paisController;

    public Database() {
        this.paises = new ArrayList<Pais>();
        this.estadoController = new EstadoController();
        this.regiaoController = new RegiaoController();
        this.paisController = new PaisController();
    }

    public boolean save(Territorio territorio) {
        if (territorio instanceof Pais) {
            this.setPaises(paisController.save(this.paises, (Pais) territorio));
            return true;
        }
        return false;
    }

    public boolean save(Territorio territorio, String siglaPais) {
        if (territorio instanceof Regiao) {
            this.setPaises(regiaoController.save(this.paises, (Regiao) territorio, siglaPais));
            return true;
        } else {
            return false;
        }
    }

    public boolean save(Territorio territorio, String siglaPais, String nomeRegiao) {
        if (territorio instanceof Estado) {
            this.setPaises(estadoController.save(this.paises, (Estado) territorio, siglaPais, nomeRegiao));
            return true;
        } else {
            return false;
        }
    }

    public Pais findPais(String sigla) {
        return paisController.get(paises, sigla);
    }

    public Regiao findRegiao(String sigla) {
        return regiaoController.get(paises, sigla);
    }


    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
    }
}
