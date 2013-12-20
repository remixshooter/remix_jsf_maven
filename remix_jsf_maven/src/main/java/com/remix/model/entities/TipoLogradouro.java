package com.remix.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tipologradouro")
public class TipoLogradouro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdTipoLogradouro", nullable = false)
    private Integer idTipoLogroudouro;
    @Column(name = "DescricaoTipoLogradouro", nullable = false, length = 40)
    private String descricaoTipoLogradouro;

    @OneToMany(mappedBy = "tipologradouro", fetch = FetchType.LAZY)
    @ForeignKey(name = "EnderecoTipoLogradouro")
    private List<Endereco> enderecos;

    public TipoLogradouro() {
    }

    public Integer getIdTipoLogroudouro() {
        return idTipoLogroudouro;
    }

    public void setIdTipoLogroudouro(Integer idTipoLogroudouro) {
        this.idTipoLogroudouro = idTipoLogroudouro;
    }

    public String getDescricaoTipoLogradouro() {
        return descricaoTipoLogradouro;
    }

    public void setDescricaoTipoLogradouro(String descricaoTipoLogradouro) {
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idTipoLogroudouro != null ? this.idTipoLogroudouro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoLogradouro other = (TipoLogradouro) obj;
        if (this.idTipoLogroudouro != other.idTipoLogroudouro && (this.idTipoLogroudouro == null || !this.idTipoLogroudouro.equals(other.idTipoLogroudouro))) {
            return false;
        }
        return true;
    }
}