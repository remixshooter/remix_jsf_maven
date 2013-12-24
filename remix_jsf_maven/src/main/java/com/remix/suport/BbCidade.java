package com.remix.suport;

import com.remix.model.dao.HibernateDAO;
import com.remix.model.dao.InterfaceDAO;
import com.remix.model.entities.Cidade;
import com.remix.model.entities.Sexo;
import com.remix.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbCidade")
@RequestScoped
public class BbCidade implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<Cidade> getCidades(){
        InterfaceDAO<Cidade> cidadeDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadeDAO.getEntities();
    }
}
