package Database;

import Entity.Territorio;

public interface DataManipulator {

    public boolean save(Territorio territorio);

    public boolean save(Territorio territorio, String siglaPais);
    public boolean save(Territorio territorio, String siglaPais, String nomeRegiao);
}
