package VW.DAO;

import VW.conexion.Conexion;
import VW.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static VW.conexion.Conexion.getConexion;

// DAO Data Access Object
public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiantes_db.estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch(Exception e){
            System.out.println("Ocurrio un error: "+e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Ocurrio un error al cerrar: "+e.getMessage());
            }
        }
        return estudiantes;
    }

    // findbyid
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiantes_db.estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }

        } catch(Exception e){
            System.out.println("Ocurrio un error: "+e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch(Exception e){
                System.out.println("Ocurrio un error al cerrar conexión: "+e.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email)  VALUES(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        } catch(Exception e){
            System.out.println("Ocurrio un error al agregar estudiante: "+e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch(Exception e){
                System.out.println("Ocurrio un error al cerrar conexión: "+e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarEstudiantes(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch(Exception e){
            System.out.println("Error al modificar: "+e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion: "+e.getMessage());
            }
        }
        return false;
    }

    public boolean eliminar(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Hubo un problema con la eliminación: "+ e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("No se ha cerrado el sistema"+e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();
        // Agregar estudiante
        //var nuevoEstudiante = new Estudiante("Montes", "Arellano", "55678987", "Monte@hotmail.com");
        //var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        //if (agregado) {
        //    System.out.println("Estudiante agregado: " + nuevoEstudiante);
        //}
        //else {
        //    System.out.println("No se agrego estudiante: " + nuevoEstudiante);
        //}

        // Modificamos un estudiante existente
        //var estudiantemodificar = new Estudiante(1,"Rodrigo","Infante","22997788","rodrigo@hotmail.com");
        //var modificado = estudianteDao.modificarEstudiantes(estudiantemodificar);
        //if(modificado)
        //    System.out.println("Estudiante modificado: "+estudiantemodificar);
        //else
        //    System.out.println("No se pudo modificar: " + estudiantemodificar);

        // Eliminar estudiantes
        var estudianteEliminar = new Estudiante(2);
        var eliminado = estudianteDao.eliminar(estudianteEliminar);
        if(eliminado)
            System.out.println("Estudiante eliminado: "+ estudianteEliminar);
        else
            System.out.println("No se elimino estudiante: "+ estudianteEliminar);



        // Listar los estudiantes

        System.out.println("Listado Estudiantes");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        // Buscar por id
        //var estudiante1 = new Estudiante(3);
        //System.out.println("Estudiante antes de la busqueda: "+estudiante1);
        //var econtrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        //if(econtrado){
        //    System.out.println("Estudiante encontrado: "+estudiante1);
        //}
        //else {
        //    System.out.println("No se encontró estudiante: "+estudiante1.getIdEstudiante());
        //}
    }
}
