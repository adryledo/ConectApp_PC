/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

/**
 *
 * @author ADRIANLC
 */
public class GestorVentanas
{
    private static boolean contactosAbierta = false;
    private static boolean gruposAbierta = false;
    private static boolean informesAbierta = false;

    public static boolean isGruposAbierta() {
        return gruposAbierta;
    }

    public static void setGruposAbierta(boolean gruposAbierta) {
        GestorVentanas.gruposAbierta = gruposAbierta;
    }

    public static boolean isInformesAbierta() {
        return informesAbierta;
    }

    public static void setInformesAbierta(boolean informesAbierta) {
        GestorVentanas.informesAbierta = informesAbierta;
    }
    
    public static boolean isContactosAbierta() {
        return contactosAbierta;
    }
    
    public static void setContactosAbierta(boolean contactos)
    {
        GestorVentanas.contactosAbierta = contactos;
    }

    /*public static VentanaConver recuperarVentanaConver(VentanaPrincipal principal, 
            Contacto contacto) {
        VentanaConver v;
        for(Object o : principal.getEscritorio().getAllFrames())
        {
            if(o.getClass().equals(VentanaConver.class))
            {
                v = (VentanaConver) o;
                if(v.getContacto().equals(contacto))
                {
                    return v;
                }
            }
        }
       /* Contacto c = null;
        if((c = EjecutarMetodoServ.recuperarContacto(contacto)) == null)
        {
            c = new Contacto();
            c.setIp(host);
            c.setNombre(host);
        }*/
        
    /*    v = new VentanaConver(principal, contacto);
        principal.getEscritorio().add(v);
        return v;
    }*/
}
