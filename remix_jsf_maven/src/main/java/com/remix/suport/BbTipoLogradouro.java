package com.remix.suport;

import com.remix.model.dao.HibernateDAO;
import com.remix.model.dao.InterfaceDAO;
import com.remix.model.entities.TipoLogradouro;
import com.remix.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbTipoLogradouro")
@RequestScoped
public class BbTipoLogradouro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<TipoLogradouro> getTipoLogradouro(){
        InterfaceDAO<TipoLogradouro> tipoLogradouroDAO = new HibernateDAO<TipoLogradouro>(TipoLogradouro.class, FacesContextUtil.getRequestSession());
        return tipoLogradouroDAO.getEntities();
    }
}
