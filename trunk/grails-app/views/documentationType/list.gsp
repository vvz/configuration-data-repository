  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>DocumentationType List</title>
    </head>
    <body>
        <div class="body">
            <h1>DocumentationType List</h1>
            <div class="nav">%{----}%
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New DocumentationType</g:link></span>
            </div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="order" title="Order" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${documentationTypeList}" status="i" var="documentationType">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            %{--<td><g:link action="show" id="${documentationType.id}">${documentationType.id?.encodeAsHTML()}</g:link></td>--}%
                        
                            <td><g:link action="show" id="${documentationType.id}">${documentationType.description?.encodeAsHTML()}</g:link></td>
                        
                            <td>${documentationType.order?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${DocumentationType.count()}" />
            </div>
        </div>
    </body>
</html>
