package org.sagittarius90.service.utente;

import org.sagittarius90.database.adapter.UtenteDbAdapter;
import org.sagittarius90.database.entity.Utente;
import org.sagittarius90.io.utente.UtenteConverterImpl;
import org.sagittarius90.model.UtenteModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class UtenteService extends BaseServiceImpl<UtenteModel> {

    @Override
    public List<UtenteModel> getCollection(BaseFilter baseFilter) {
        List<Utente> utenti = UtenteDbAdapter.getInstance().getAllUtenti();
        return getUtenteConverter().createFromEntities(utenti);
    }

    @Override
    public UtenteModel getSingleResultById(String id) {
        if (UtenteModel.ANONYMOUS_UTENTE_ID.equals(id)) {
            return null;
        }

        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Utente utente = UtenteDbAdapter.getInstance().getUtenteById((int) realId);
        return getUtenteConverter().createFrom(utente);
    }

    @Override
    public UtenteModel create(UtenteModel fromModel) {
        Utente utente = getUtenteConverter().createFrom(fromModel);

        try {
            UtenteDbAdapter.getInstance().createNewUtente(utente);
        } catch (Exception e) {
            return null;
        }

        return getUtenteConverter().createFrom(utente);
    }

    @Override
    public boolean update(String id, UtenteModel fromModel) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Utente utente = UtenteDbAdapter.getInstance().getUtenteById((int)realId);
        utente = getUtenteConverter().updateEntity(utente, fromModel);

        try {
            UtenteDbAdapter.getInstance().commit(utente);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        try {
            //UserDbAdapter.getInstance().deleteUserById((int)realId);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private UtenteConverterImpl getUtenteConverter() {
        return new UtenteConverterImpl();
    }
}
