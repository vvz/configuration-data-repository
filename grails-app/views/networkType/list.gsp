  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Network Type List</title>
    </head>
    <body>
        <div class="body">
            <h1>Network Type List</h1>
            <div class="nav">%{----}%
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New Network Type</g:link></span>
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
                    <g:each in="${networkTypeList}" status="i" var="networkType">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            %{--<td><g:link action="show" id="${networkType.id}">${networkType.id?.encodeAsHTML()}</g:link></td>--}%
                        
                            <td><g:link action="show" id="${networkType.id}">${networkType.description?.encodeAsHTML()}</g:link></td>
                        
                            <td>${networkType.order?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${NetworkType.count()}" />
            </div>
        </div>
    </body>
</html>
