package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak4BrisanjeVrednosti {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;


    public static void main(String[] args) {

        ConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            robaDao = DaoManager.createDao(connectionSource, Roba.class);


            List<Roba> robaZaBrisanje = robaDao.queryForEq(Roba.POLJE_NAZIV, "Voda");
            Roba roba =robaZaBrisanje.get(0);
            robaDao.delete(roba);

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
