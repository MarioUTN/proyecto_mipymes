package proyecto_mipymes.conexion_postgres;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Connection_Postgres {
	public static String getMiDireccion_IP(){
        String resp="";
        try {
            Enumeration<NetworkInterface> interfaces=NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {                
                NetworkInterface interfaz=interfaces.nextElement();
                Enumeration<InetAddress> direcciones=interfaz.getInetAddresses();
                while (direcciones.hasMoreElements()) {                    
                    InetAddress direccion=direcciones.nextElement();
                    if(direccion instanceof Inet4Address){
                        resp=direccion.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error -> "+e.getMessage());
        }
        return resp;
    }
}
