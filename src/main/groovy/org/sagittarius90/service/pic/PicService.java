package org.sagittarius90.service.pic;

import org.sagittarius90.database.adapter.PicDbAdapter;
import org.sagittarius90.database.entity.Pic;
import org.sagittarius90.io.pic.PicConverterImpl;
import org.sagittarius90.model.PicModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class PicService extends BaseServiceImpl<PicModel> {

    @Override
    public List<PicModel> getCollection(BaseFilter baseFilter) {
        //List<Pic> pics = PicDbAdapter.getInstance().getAllPics();
        //return getPicConverter().createFromEntities(pics);
        return null;
    }

    @Override
    public PicModel getSingleResultById(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Pic pic = PicDbAdapter.getInstance().getPicById((int) realId);
        return getPicConverter().createFrom(pic);
    }

    @Override
    public PicModel create(PicModel fromModel) {
        Pic pic = getPicConverter().createFrom(fromModel);

        try {
            PicDbAdapter.getInstance().createNewPic(pic);
        } catch (Exception e) {
            return null;
        }

        return getPicConverter().createFrom(pic);
    }

    @Override
    public boolean update(String id, PicModel fromModel) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Pic pic = PicDbAdapter.getInstance().getPicById((int)realId);
        pic = getPicConverter().updateEntity(pic, fromModel);

        try {
            PicDbAdapter.getInstance().commit(pic);
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
            //PicDbAdapter.getInstance().deletePicById((int)realId);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public PicConverterImpl getPicConverter() {
        return new PicConverterImpl();
    }
}
