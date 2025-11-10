package Modelo;

import java.util.ArrayList;

public class ControladorDeElevadores {

    private ArrayList<Elevador> listaDeElevadores;
    private ArrayList<Peticion> peticionesPendientes;

    public ControladorDeElevadores(int numElevadores) {
        listaDeElevadores = new ArrayList<>();
        peticionesPendientes = new ArrayList<>();
        for(int i=1; i<=numElevadores; i++){
            listaDeElevadores.add(new ElevadorDePasajeros(i));
        }
    }

    public void recibirPeticion(Peticion p){
        peticionesPendientes.add(p);
        asignarPeticion();
    }

    private void asignarPeticion(){
        if(peticionesPendientes.isEmpty()) return;
        Peticion p = peticionesPendientes.remove(0);
        Elevador elegido = null;
        int mejorDistancia = Integer.MAX_VALUE;
        for(Elevador e : listaDeElevadores){
            int d = Math.abs(e.getPisoActual() - p.getPisoOrigen());
            if(d < mejorDistancia){
                mejorDistancia = d;
                elegido = e;
            }
        }
        if(elegido != null){
            elegido.agregarDestino(p.getPisoOrigen());
            elegido.agregarDestino(p.getPisoDestino());
        }
    }

    public void actualizarSistema(){
        for(Elevador e : listaDeElevadores){
            e.mover();
        }
    }

    public ArrayList<Elevador> getListaDeElevadores(){
        return listaDeElevadores;
    }
}

