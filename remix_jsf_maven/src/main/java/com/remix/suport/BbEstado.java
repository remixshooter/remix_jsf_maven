package com.remix.suport;

import com.remix.model.dao.HibernateDAO;
import com.remix.model.dao.InterfaceDAO;
import com.remix.model.entities.Estado;
import com.remix.model.entities.Sexo;
import com.remix.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbEstado")
@RequestScoped
public class BbEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<Estado> getEstados(){
        InterfaceDAO<Estado> estadoDAO = new HibernateDAO<Estado>(Estado.class, FacesContextUtil.getRequestSession());
        return estadoDAO.getEntities();
    }
}
