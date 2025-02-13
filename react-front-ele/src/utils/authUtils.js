export const getRoleFromToken = (token) => {
    try {
         
        const payloadBase64 = token.split(".")[1];
        const payload = JSON.parse(atob(payloadBase64));
        return payload.role || "Sin rol";
    } catch (error) {
        console.error("Error al decodificar el token:", error);
        return "Sin rol";
    }
};
