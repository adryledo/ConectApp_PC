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
    private static boolean selContactoAbierta = false;
//    private static ArrayList<VentanaConver> arrayConversaciones = new ArrayList<>();

    static boolean isGruposAbierta() {
        return gruposAbierta;
    }

    static void setGruposAbierta(boolean gruposAbierta) {
        GestorVentanas.gruposAbierta = gruposAbierta;
    }

    static boolean isInformesAbierta() {
        return informesAbierta;
    }

    static void setInformesAbierta(boolean informesAbierta) {
        GestorVentanas.informesAbierta = informesAbierta;
    }
    
    static boolean isContactosAbierta() {
        return contactosAbierta;
    }
    
    static void setContactosAbierta(boolean contactos)
    {
        GestorVentanas.contactosAbierta = contactos;
    }

    static boolean isSelContactoAbierta() {
        return selContactoAbierta;
    }

    static void setSelContactoAbierta(boolean selContactoAbierta) {
        GestorVentanas.selContactoAbierta = selContactoAbierta;
    }
    
/*    static void addVentanaConver(VentanaConver vc) {
        GestorVentanas.arrayConversaciones.add(vc);
    }*/
    
/*    static VentanaConver recuperarVentanaConver(VentanaPrincipal principal, 
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
        /*Contacto c = null;
        if((c = EjecutarMetodoServ.recuperarContacto(contacto)) == null)
        {
            c = new Contacto();
            c.setIp(host);
            c.setNombre(host);
        }
        
        v = new VentanaConver(principal, contacto);
        principal.getEscritorio().add(v);
        return v;
    }*/
}
