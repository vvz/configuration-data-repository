  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>RelationReference List</title>
    </head>
    <body>
        <div class="body">
            <h1>RelationReference List</h1>
            <div class="nav">%{----}%
                %{--<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>--}%
                <span class="menuButton"><g:link class="create" action="create">New RelationReference</g:link></span>
            </div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="order" title="Order" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${relationReferenceList}" status="i" var="relationReference">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            %{--<td><g:link action="show" id="${relationReference.id}">${relationReference.id?.encodeAsHTML()}</g:link></td>--}%
                        
                            <td><g:link action="show" id="${relationReference.id}">${relationReference.name?.encodeAsHTML()}</g:link></td>
                        
                            <td>${relationReference.description?.encodeAsHTML()}</td>
                        
                            <td>${relationReference.order?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${RelationReference.count()}" />
            </div>
        </div>
    </body>
</html>
