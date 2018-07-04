/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoas;

import DAO.PessoaJpaController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "pessoa", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nome"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
    , @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id")
    , @NamedQuery(name = "Pessoa.findByPessoaId", query = "SELECT p FROM Pessoa p WHERE p.PessoaId = :PessoaId")
    , @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome")
    , @NamedQuery(name = "Pessoa.findByNumero", query = "SELECT p FROM Pessoa p WHERE p.numero = :numero")
    , @NamedQuery(name = "Pessoa.findByMarca", query = "SELECT p FROM Pessoa p WHERE p.marca = :marca")})
public class Pessoa implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "pessoaid", nullable = false, length = 6)
    private String pessoaid;
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Basic(optional = false)
    @Column(name = "Numero", nullable = false, length = 300)
    private String numero;
    @Basic(optional = false)
    @Column(name = "marca", nullable = false, length = 6)
    private String marca;
 
   

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(Long id, String nome,String pessoaid, String numero, String marca) {
        this.id = id;
        this.pessoaid = pessoaid;
        this.nome = nome;
        this.numero = numero;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }
public String getPessoaId() {
        return nome;
    }

    public void setPessoaId(String pessoaid) {
        String oldPessoaId = this.pessoaid;
        this.pessoaid = pessoaid;
        changeSupport.firePropertyChange("pessoaid", oldPessoaId, pessoaid);
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        String oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        String oldMarca = this.marca;
        this.marca = marca;
        changeSupport.firePropertyChange("marca", oldMarca, marca);
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle.Pessoa[ id=" + id + " ]";
    }

    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDpessoaPU");
            PessoaJpaController pessoaJpaController = new PessoaJpaController(emf);
            pessoaJpaController.create(this);
            JOptionPane.showMessageDialog(null, "Pessoa incluído");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDpessoaPU");
            PessoaJpaController pessoaJpaController = new PessoaJpaController(emf);
            pessoaJpaController.edit(this);
            JOptionPane.showMessageDialog(null, "Pessoa atualizado");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDpessoaPU");
            PessoaJpaController pessoaJpaController = new PessoaJpaController(emf);
            pessoaJpaController.destroy(this.getId());
            JOptionPane.showMessageDialog(null, "Pessoa excluído");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDpessoaPU");
            PessoaJpaController pessoaJpaController = new PessoaJpaController(emf);
            Pessoa pessoaAux = pessoaJpaController.findPessoa(id);
            if (pessoaAux != null) {
                this.setId(pessoaAux.getId());
                this.setNome(pessoaAux.getNome());
                this.setNumero(pessoaAux.getNumero());
                this.setMarca(pessoaAux.getMarca());
              
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Pessoa não encontrado");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public Pessoa encontradoNome(String nome) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDpessoaPU");
            PessoaJpaController pessoaJpaController = new PessoaJpaController(emf);
            Pessoa pessoaAux = (Pessoa) pessoaJpaController.findNome(nome);
            if (pessoaAux != null) {
                this.setId(pessoaAux.getId());
                this.setNome(pessoaAux.getNome());
                this.setNumero(pessoaAux.getNumero());
                this.setMarca(pessoaAux.getMarca());
               
                return pessoaAux;
            } else {
                JOptionPane.showMessageDialog(null, "Pessoa não encontrado");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
