package mx.unam.aragon.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.unam.aragon.dp.Empleado;

public class ArchivoEmpleado {

    private String archivo;

    public ArchivoEmpleado() {
    }

    public ArchivoEmpleado(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public void guardarEmpleados(ArrayList<Empleado> datos) throws IOException {
        // aqui el codigo para grabar ed Disco Duro
        try {
            ObjectOutputStream fSalida = new ObjectOutputStream(
                    new FileOutputStream(archivo));
            fSalida.writeObject(datos);
            fSalida.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo:"+e.toString());
            throw new IOException("Error al Guardar el archivo:"+this.archivo);
        }
    }

    public ArrayList<Empleado> leerEmpleados() throws IOException,ClassNotFoundException{
        ArrayList<Empleado> tmp = null;

        try {
            ObjectInputStream fLectura = new ObjectInputStream(
                    new FileInputStream(archivo));

            tmp = (ArrayList<Empleado>) fLectura.readObject();
            if (tmp == null) {
                System.out.println("No hay nada");
            } else {
                System.out.println("Nombre" + tmp.size());
            }
            fLectura.close();

        } catch (IOException  ex) {
            System.out.println("Error al leer el archivo" + ex.toString());
            throw new IOException("Error al leer el archivo:"+this.archivo);
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("Error al leer el archivo:"+this.archivo);
        }
        return tmp;
    }

}