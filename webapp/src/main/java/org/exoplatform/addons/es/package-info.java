/*
 * Copyright 2013 eXo Platform SAS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@juzu.Application(defaultController = IndexingManagementApplication.class)
@Portlet
@Scripts({
    @Script(id = "apiClient", value = "scripts/rest/client/ApiClient.js"),
    @Script(id = "indexingOperationResource", value = "scripts/rest/model/AnIndexingOperationResource.js"),
    @Script(id = "indexingConnectorResources", value = "scripts/rest/model/AnIndexingConnectorResources.js"),
    @Script(id = "indexingManagementApi" , value = "scripts/rest/api/VindexingManagementApi.js", depends = {"apiClient"}),
    @Script(id = "statController" , value = "scripts/app/controller/statController.js", depends = {"indexingManagementApi"}),
    @Script(id = "connectorController" , value = "scripts/app/controller/connectorController.js", depends = {"indexingManagementApi","indexingOperationResource"}),
    @Script(id = "operationController" , value = "scripts/app/controller/operationController.js", depends = {"indexingManagementApi", "indexingOperationResource"}),
    @Script(id = "main" , value = "scripts/app/main.js", depends = {"statController","connectorController","operationController"})
})
@Less({
    @Stylesheet(id = "indexingManagement-less", value = "styles/indexingManagement.less")
})
@Assets("*")
package org.exoplatform.addons.es;

import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Scripts;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.less4j.Less;
import juzu.plugin.portlet.Portlet;