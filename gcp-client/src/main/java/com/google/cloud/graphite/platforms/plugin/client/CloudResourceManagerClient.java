/*
 * Copyright 2019 Google LLC
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

package com.google.cloud.graphite.platforms.plugin.client;

import static com.google.cloud.graphite.platforms.plugin.client.util.ClientUtil.processResourceList;

import com.google.api.services.cloudresourcemanager.model.Project;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Comparator;

/**
 * Client for communicating with the Google Cloud Research Manager API.
 *
 * @see <a href="https://cloud.google.com/resource-manager/reference/rest/">Cloud Research Manager
 *     API</a>
 */
public class CloudResourceManagerClient {
  private final CloudResourceManagerWrapper cloudResourceManager;

  /**
   * Constructs a new {@link CloudResourceManagerClient} instance.
   *
   * @param cloudResourceManager The {@link CloudResourceManagerWrapper} instance this class will
   *     utilize for interacting with the Cloud Resource Manager API.
   */
  public CloudResourceManagerClient(final CloudResourceManagerWrapper cloudResourceManager) {
    this.cloudResourceManager = Preconditions.checkNotNull(cloudResourceManager);
  }

  /**
   * Retrieves a list of Projects for the credentials associated with this client.
   *
   * @return The retrieved list of projects sorted by project ID.
   * @throws IOException When an error occurred attempting to get the projects.
   */
  public ImmutableList<Project> listProjects() throws IOException {
    return processResourceList(
        cloudResourceManager.listProjects(), Comparator.comparing(Project::getProjectId));
  }
}
