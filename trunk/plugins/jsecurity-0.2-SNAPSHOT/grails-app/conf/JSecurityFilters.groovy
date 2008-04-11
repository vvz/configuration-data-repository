/*
 * Copyright 2007 Peter Ledbrook.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.jsecurity.SecurityUtils

/**
 * <p>A Grails filter that implements the same functionality as the old
 * JsecAuthBase abstract controller. It uses the role and permission
 * maps injected into the filter to determine whether the current user
 * has the required rights for a particular request.</p>
 * <p>The role and permission maps are populated via the 'accessControl'
 * property on controllers.</p>
 */
class JSecurityFilters {
    def filters = {
        accessControlCheck(controller: '*', action: '*') {
            before = {
                // Check that the current user is authenticated and
                // has permission to access the current action.
                def securityContext = SecurityUtils.securityContext

                // Get the role and permission maps for this controller.
                def roleMap = getRoleMap(controllerName)
                def permissionMap = getPermissionMap(controllerName)

                // Is this action configured for access control?
                if (!roleMap.containsKey(actionName) && !permissionMap.containsKey(actionName) &&
                        !roleMap.containsKey('*') && !permissionMap.containsKey('*')) {
                    // This action has no access control requirements,
                    // so the user does not need to be logged in to
                    // access it.
                    return true
                }

                // Is the user authenticated?
                if (!securityContext.authenticated) {
                    // Save the original URI that the user is attempting to
                    // access.
                    session.originalRequestParams = params

                    // User is not authenticated, so redirect to the login page.
                    redirect(controller: 'auth', action: 'login')
                    return false
                }

                // First check any required roles.
                def requiredRoles = roleMap[actionName]
                if (requiredRoles == null) requiredRoles = []
                if (roleMap['*']) {
                    // Add any roles that apply to all actions.
                    requiredRoles.addAll(roleMap['*'])
                }

                if (!requiredRoles.isEmpty() && !securityContext.hasAllRoles(requiredRoles)) {
                    // User does not have the required roles. Redirect
                    // to an error page?
                    redirect(controller: 'auth', action: 'unauthorized')
                    return false
                }

                // Now check any required permissions.
                def requiredPermissions = permissionMap[actionName]
                if (requiredPermissions == null) requiredPermissions = []
                if (permissionMap['*']) {
                    // Add any permission that applies to all actions.
                    requiredPermissions.addAll(permissionMap['*'])
                }

                if (!requiredPermissions.isEmpty() && !securityContext.impliesAll(requiredPermissions)) {
                    // User does not have the requisite permissions -
                    // redirect to an error page?
                    redirect(controller: 'auth', action: 'unauthorized')
                    return false
                }

                // User has passed all checks, so they may access the
                // action.
                return true
            }
        }
    }
}
