/*
Instruciones: Taller sobre Hilos sincronizados 
Sistema de un restaurante.
El número de cocineros son 6
10 camareros que también serán hilos cada uno. 
1 cajero que se encarga de ingresar los pedidos que son enviados inmediatamente a la cocina 
*/

package JavaThreadsSynchronized;

public class Menu {

    public static void main(String[] args) {
        
        //Este hilo inicia a todos los otros hilos y tiene que recibir como parametro el plato
        //Los unicos parametros que pueden ingresar son Hamburguesa, Chalupas, Papas, Perros, Cazados 
        Cajero cajero = new Cajero("Hamburguesa");
        
        //Estos hilos dependen del hilo Cajero, sin el hilo Cajero y la variable plato, no podrian ejecutarse 
        Chefs chefs0 = new Chefs(cajero);
        Chefs chefs1 = new Chefs(cajero);
        Chefs chefs2 = new Chefs(cajero);
        Chefs chefs3 = new Chefs(cajero);
        Chefs chefs4 = new Chefs(cajero);
        Chefs chefs5 = new Chefs(cajero);
        
        //Estos hilos dependen de los hilos Chefs, no se van a ejecutar hasta que los hilos Chefs hayan sido ejecutados 
        Camarero camarero0 = new Camarero(chefs0);
        Camarero camarero1 = new Camarero(chefs1);
        Camarero camarero2 = new Camarero(chefs2);
        Camarero camarero3 = new Camarero(chefs3);
        Camarero camarero4 = new Camarero(chefs4);
        Camarero camarero5 = new Camarero(chefs5);
        Camarero camarero6 = new Camarero(chefs0);
        Camarero camarero7 = new Camarero(chefs1);
        Camarero camarero8 = new Camarero(chefs2);
        Camarero camarero9 = new Camarero(chefs3);
        
        //Esto es para iniciar la ejecucion
        cajero.start();
        
    }


/*
Aqui empieza la clase Cajero, contiene el primer hilo para empezar a ser ejecutado y necesita como parametro el plato que ordena el cliente    
*/    
static class Cajero extends Thread{
    private String plato;
    public Cajero(String plato){ this.plato = plato; }
    @Override
     public void run(){
        System.out.println(getName() + plato);                        
    }
}

/*
Este hilo no se ejecuta hasta no recibir el hilo de Cajero
*/
static class Chefs extends Thread{    
   private String plato;
   public Chefs(String plato){ this.plato = plato; }
   
   public Chefs(Thread opcion){
        this.opcion=opcion;
    }
   @Override
        public void run(){
        System.out.println(getName());
        //Cocinar un cazado dura 3000 milesegundos
        if ("Hamburguesa" == plato){
                        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        }
        
        //Cocinar un Chalupas dura 5000 milesegundos        
        if ("Chalupas" == plato){ 
                        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        }
                        
        
        //Cocinar un papas fritas dura 2000 milesegundos        
        if ("Papas" == plato){
                        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        }
                        
        //Cocinar un perro dura 4000 milesegundos        
        if("Perros" == plato){
                        try{
            Thread.sleep(4000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        }
                        
        //Cocinar un cazado dura 6000 milesegundos
        if ("Cazados" == plato){
                        try{            
            Thread.sleep(6000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }        
        }
    }
        
        private Thread opcion;
}




/*
Estos hilos no se ejecutan, hasta que no reciban los hilos de la clase Chefs
*/
static class Camarero extends Thread{    
    public Camarero(Thread plato){
        this.plato=plato;
    }
    public void run(){
        System.out.println(getName());
        try{
            plato.join();
        }catch(InterruptedException e){
            //e1.printStackTrace();
        }
        try{
            //Los camareros duran entre 2000 a 6000 milesgundos, este punto calcula eso
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private Thread plato;
}
}
