/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SephiRoth
 */
@Entity
@Table(name = "proyeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyeccion.findAll", query = "SELECT p FROM Proyeccion p")
    , @NamedQuery(name = "Proyeccion.findByIdProyeccion", query = "SELECT p FROM Proyeccion p WHERE p.idProyeccion = :idProyeccion")
    , @NamedQuery(name = "Proyeccion.findByNumeroDiente", query = "SELECT p FROM Proyeccion p WHERE p.numeroDiente = :numeroDiente")
    , @NamedQuery(name = "Proyeccion.findByPresupuestoInicial", query = "SELECT p FROM Proyeccion p WHERE p.presupuestoInicial = :presupuestoInicial")
    , @NamedQuery(name = "Proyeccion.findByPresupuestoActual", query = "SELECT p FROM Proyeccion p WHERE p.presupuestoActual = :presupuestoActual")
    , @NamedQuery(name = "Proyeccion.findByEstado", query = "SELECT p FROM Proyeccion p WHERE p.estado = :estado")
    , @NamedQuery(name = "Proyeccion.findByFechaRegistro", query = "SELECT p FROM Proyeccion p WHERE p.fechaRegistro = :fechaRegistro")})
public class Proyeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyeccion")
    private Integer idProyeccion;
    @Column(name = "numero_diente")
    private Integer numeroDiente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "presupuesto_inicial")
    private Double presupuestoInicial;
    @Column(name = "presupuesto_actual")
    private Double presupuestoActual;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente idPaciente;
    @JoinColumn(name = "id_procedimiento", referencedColumnName = "id_procedimiento")
    @ManyToOne
    private Procedimiento idProcedimiento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idProyeccion")
    private Collection<Abono> abonoCollection;

    public Proyeccion() {
    }

    public Proyeccion(Integer idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public Integer getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(Integer idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public Integer getNumeroDiente() {
        return numeroDiente;
    }

    public void setNumeroDiente(Integer numeroDiente) {
        this.numeroDiente = numeroDiente;
    }

    public Double getPresupuestoInicial() {
        return presupuestoInicial;
    }

    public void setPresupuestoInicial(Double presupuestoInicial) {
        this.presupuestoInicial = presupuestoInicial;
    }

    public Double getPresupuestoActual() {
        return presupuestoActual;
    }

    public void setPresupuestoActual(Double presupuestoActual) {
        this.presupuestoActual = presupuestoActual;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Procedimiento getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Procedimiento idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<Abono> getAbonoCollection() {
        return abonoCollection;
    }

    public void setAbonoCollection(Collection<Abono> abonoCollection) {
        this.abonoCollection = abonoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyeccion != null ? idProyeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyeccion)) {
            return false;
        }
        Proyeccion other = (Proyeccion) object;
        if ((this.idProyeccion == null && other.idProyeccion != null) || (this.idProyeccion != null && !this.idProyeccion.equals(other.idProyeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Proyeccion[ idProyeccion=" + idProyeccion + " ]";
    }
    
}
