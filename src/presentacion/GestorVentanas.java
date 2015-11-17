/*
 * Copyright (C) 2015 Adrián Ledo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package presentacion;

/**
 *
 * @author Adrian Ledo
 */
public class GestorVentanas
{
    private static boolean contactosAbierta = false;
    private static boolean gruposAbierta = false;
    private static boolean informesAbierta = false;
    private static boolean selContactoAbierta = false;

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
}
