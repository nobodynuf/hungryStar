<%-- 
    Document   : home
    Created on : 16-nov-2017, 20:14:58
    Author     : nobodynuf
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<script>
    cambiarTitulo("Inicio");
</script>

<h1>ESTA PAGINA SE SUPONE QUE TENDRA COLORES Y COSAS BONITAS</h1>
<h1 style="color:red">
    <c:if test="${requestScope.error!=null}">
        <c:out value="${requestScope.error}"></c:out>
    </c:if>
</h1>

<p>


    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sit amet sapien et metus bibendum molestie vel sit amet velit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque ultrices volutpat laoreet. Morbi quam urna, venenatis aliquet fermentum sed, ultrices in ex. In vitae finibus libero. Curabitur gravida mattis maximus. Suspendisse elementum enim ac metus auctor imperdiet. Aliquam fermentum quis turpis sed ullamcorper. Morbi condimentum, neque et porttitor vehicula, ipsum dolor blandit enim, at interdum elit enim nec mauris.

    Duis convallis tortor lectus, quis ornare lectus porttitor nec. Integer auctor eros vitae risus dapibus, a feugiat ante faucibus. Etiam at metus non mi dignissim ultrices vitae at felis. Suspendisse elit ligula, pretium vitae dui nec, rhoncus ultrices mi. Proin ultricies nibh vitae odio elementum, nec sagittis metus rutrum. Vestibulum eros ante, tempor in molestie nec, bibendum nec neque. Ut tristique, ipsum non tincidunt fringilla, ex tortor tempor ligula, ac hendrerit dolor elit eget dui. Curabitur malesuada ante suscipit, commodo nisl id, finibus velit. Fusce et mi blandit mauris pretium viverra. Donec vitae laoreet ante. Sed accumsan, nibh hendrerit auctor dapibus, orci ipsum volutpat libero, elementum facilisis nibh orci ac eros. Pellentesque eget ultrices arcu, eu ultrices enim. Phasellus maximus vestibulum ipsum, eu scelerisque risus accumsan sed. Phasellus a massa sit amet nisl vulputate commodo.

    Praesent a ultrices diam. Etiam vel egestas libero, id molestie urna. Aliquam cursus magna blandit, pellentesque velit eu, porttitor leo. Praesent non sapien gravida, lacinia turpis quis, sodales dui. Duis facilisis felis velit, id sagittis justo ultricies sed. Ut feugiat sapien varius gravida commodo. Pellentesque dui turpis, imperdiet vel pharetra et, lobortis non risus. Nam commodo tristique tellus eget rhoncus. Mauris non turpis ac justo venenatis volutpat laoreet sed lectus. Aenean nec mauris commodo lorem scelerisque cursus sed ac sapien. Integer at vehicula elit. Nullam nec feugiat mi, id gravida nisi. Sed enim quam, lacinia at mauris nec, vehicula aliquet elit. Aliquam iaculis ipsum commodo lacus vehicula consequat.

    Fusce sit amet sapien congue, dictum felis sed, dictum ex. Suspendisse potenti. Cras nec egestas ex. Pellentesque tempus ultricies metus. Donec leo felis, placerat et hendrerit non, aliquet a lorem. Praesent eget placerat nunc. Aliquam erat volutpat. Morbi accumsan facilisis purus, rhoncus dignissim enim varius vitae. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Ut nec velit id nunc pretium bibendum ut tincidunt eros. In vel dictum urna. Vivamus porta nec justo et faucibus. Suspendisse suscipit bibendum orci, in bibendum urna bibendum id.

    Mauris placerat ipsum non nulla venenatis rutrum. Suspendisse sed ligula ac felis pulvinar placerat ac eget augue. Duis ante ligula, viverra sed dapibus non, fermentum eget magna. Pellentesque tincidunt ullamcorper nibh. Maecenas dolor lacus, ultrices id tortor eu, venenatis pellentesque mauris. Morbi neque odio, luctus nec metus egestas, vehicula malesuada mauris. Nulla ac massa ac urna rhoncus mollis nec vel dui. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fringilla lorem et neque vestibulum malesuada.

    Vestibulum vitae arcu erat. Etiam iaculis tristique lectus, vel facilisis risus. Ut vitae pharetra metus, eu ultrices est. Nam posuere venenatis lorem, sit amet pharetra erat venenatis ut. Phasellus efficitur, est sit amet vestibulum varius, justo ligula volutpat odio, sit amet commodo elit magna posuere nunc. Donec at gravida odio. Phasellus nec lorem ut metus dignissim dictum eget sed risus. Fusce luctus pulvinar sem. Morbi nec eleifend ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam erat volutpat. Morbi maximus tincidunt enim at sagittis. Nulla facilisi. Pellentesque suscipit tellus id diam accumsan vehicula. In placerat vehicula mauris non aliquam.

    Aenean laoreet, orci vel pretium vulputate, augue leo egestas ante, sit amet mattis massa quam quis massa. Etiam vitae elementum lorem, sed placerat est. Nunc vestibulum molestie mauris vel fermentum. Phasellus id neque id est fringilla tempor malesuada eget ligula. Pellentesque viverra ante vel metus lacinia, nec maximus ligula ullamcorper. Aliquam enim lacus, lobortis eu urna sit amet, congue hendrerit ante. Quisque quis commodo ante. Suspendisse et faucibus diam. Curabitur interdum massa diam, sit amet interdum elit ultricies in. Maecenas convallis lobortis justo, eget suscipit purus scelerisque at. Donec auctor justo sapien, in aliquam tortor ullamcorper sed.

    Praesent feugiat, augue non efficitur pulvinar, metus elit vehicula nulla, nec eleifend sem massa vel libero. Etiam ornare nisl nisi, non pellentesque massa euismod ac. Pellentesque a mi in nunc suscipit pellentesque nec efficitur augue. Nam accumsan ultricies ligula vitae accumsan. Nullam euismod, diam eu rhoncus rutrum, ex dolor pulvinar risus, sed accumsan ex mauris laoreet nisl. Vivamus tempus, elit non malesuada porttitor, turpis nisl tempor sem, eget mollis leo nibh eu ex. Aenean sem nulla, tincidunt ac sollicitudin aliquam, dapibus eu risus. Nam enim justo, gravida imperdiet elementum sit amet, dignissim at tortor. Mauris semper, est nec lobortis pretium, sapien metus euismod risus, sed varius metus lectus a ante. Quisque venenatis condimentum mi non consectetur. Praesent ac finibus diam. Pellentesque imperdiet tristique volutpat.

    Proin vel condimentum quam, non mollis lacus. Cras elit mi, sollicitudin ac interdum vitae, ultricies id purus. Quisque dignissim, libero nec varius vestibulum, tortor elit viverra felis, sit amet molestie risus lectus in orci. Cras posuere in risus eu varius. Maecenas interdum, urna non condimentum sollicitudin, erat ipsum hendrerit arcu, sit amet vestibulum metus nisl in leo. Curabitur ultricies lorem tristique, vulputate velit imperdiet, porttitor odio. Pellentesque faucibus velit diam, a pellentesque neque rhoncus et. Etiam sem lacus, interdum malesuada leo ut, pellentesque fringilla dolor. Nam sed vehicula tortor, eget malesuada risus.

    Donec vitae purus non arcu pellentesque dapibus. Maecenas fringilla sapien ac est elementum, ut interdum diam condimentum. Quisque tincidunt finibus augue, porttitor feugiat nunc tincidunt sit amet. Cras lacinia quam turpis, vulputate sodales metus rhoncus vitae. Morbi tristique libero ut elit hendrerit pharetra. Maecenas blandit sit amet eros quis posuere. Curabitur cursus feugiat metus. Proin scelerisque felis malesuada purus malesuada, eget porttitor nibh commodo. Integer in massa arcu. Mauris scelerisque est et enim eleifend malesuada. Quisque quis ante lacinia leo semper malesuada sit amet sit amet ex. Aenean in suscipit turpis. Sed ut nibh aliquam dolor hendrerit feugiat. 
</p>


<%@include file="footer.jsp" %>


