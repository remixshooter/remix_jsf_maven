package com.remix.controller;

import com.remix.conversores.ConverterSHA1;
import com.remix.model.dao.HibernateDAO;
import com.remix.model.dao.InterfaceDAO;
import com.remix.model.entities.Endereco;
import com.remix.model.entities.Pessoa;
import com.remix.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MbPessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    private String confereSenha;
    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();
    private List<Pessoa> pessoas;
    private List<Endereco> enderecos;

    private InterfaceDAO<Pessoa> pessoaDAO() {
        InterfaceDAO<Pessoa> pessoaDAO = new HibernateDAO<Pessoa>(Pessoa.class, FacesContextUtil.getRequestSession());
        return pessoaDAO;
    }

    private InterfaceDAO<Endereco> enderecoDAO() {
        InterfaceDAO<Endereco> enderecoDAO = new HibernateDAO<Endereco>(Endereco.class, FacesContextUtil.getRequestSession());
        return enderecoDAO;
    }

    public String limpPessoa() {
        pessoa = new Pessoa();
        endereco = new Endereco();
        return editPessoa();
    }

    public String editPessoa() {
        return "/restrict/cadastrarpessoa.faces";
    }

    public String addPessoa() {
        Date date = new Date();
        pessoa.setSenha(ConverterSHA1.cipher(pessoa.getSenha()));

        if ((pessoa.getSenha() == null || pessoa.getSenha().length() == 0
                || confereSenha == null || confereSenha.length() == 0) == false
                && pessoa.getSenha().equals(ConverterSHA1.cipher(confereSenha))) {
            
            if (pessoa.getIdPessoa() == null || pessoa.getIdPessoa() == 0) {
                pessoa.setDataDeCadastro(date);
                insertPessoa();
            } else {
                updatePessoa();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
        }
        return null;
    }

    private void insertPessoa() {
        pessoa.setPermissao("ROLE_ADMIN");
        pessoaDAO().save(pessoa);
        endereco.setPessoa(pessoa);
        enderecoDAO().save(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updatePessoa() {
        pessoaDAO().update(pessoa);
        enderecoDAO().update(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public String deletePessoa() {
        pessoaDAO().remove(pessoa);
        enderecoDAO().remove(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluido com sucesso", ""));
        return null;
    }

    public List<Pessoa> getPessoas() {
        pessoas = pessoaDAO().getEntities();
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoDAO().getEntities();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }
}
