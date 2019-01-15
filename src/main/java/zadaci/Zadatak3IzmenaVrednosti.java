package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {


    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            robaDao = DaoManager.createDao(connectionSource, Roba.class);


            List<Roba> robaZaIzmenu = robaDao.queryForEq(Roba.POLJE_OPIS, "Plasticna stolica");
            Roba roba =robaZaIzmenu.get(0);
            roba.setOpis("Drvena stolica");
            robaDao.update(roba);

            List<Roba> roba1 = robaDao.queryForAll();
            for (Roba r : roba1)
                System.out.println(r);


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




    }

}
