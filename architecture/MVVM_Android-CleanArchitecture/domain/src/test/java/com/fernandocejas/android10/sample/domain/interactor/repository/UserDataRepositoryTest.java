/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.fernandocejas.android10.sample.domain.interactor.repository;

import com.fernandocejas.android10.sample.data.ApplicationTestCase;
import com.fernandocejas.android10.sample.data.dto.User;
import com.fernandocejas.android10.sample.data.entity.User;
import com.fernandocejas.android10.sample.data.entity.mapper.UserEntityDataMapper;
import com.fernandocejas.android10.sample.data.datasource.UserDataStore;
import com.fernandocejas.android10.sample.data.datasource.UserDataStoreFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

public class UserDataRepositoryTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 123;

  private UserDataRepository userDataRepository;

  @Mock private UserDataStoreFactory mockUserDataStoreFactory;
  @Mock private UserEntityDataMapper mockUserEntityDataMapper;
  @Mock private UserDataStore mockUserDataStore;
  @Mock private User mockUserEntity;
  @Mock private User mockUser;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    userDataRepository = new UserDataRepository(mockUserDataStoreFactory,
        mockUserEntityDataMapper);

    given(mockUserDataStoreFactory.create(anyInt())).willReturn(mockUserDataStore);
    given(mockUserDataStoreFactory.createCloudDataStore()).willReturn(mockUserDataStore);
  }

  @Test
  public void testGetUsersHappyCase() {
    List<User> usersList = new ArrayList<>();
    usersList.add(new User());
    given(mockUserDataStore.userEntityList()).willReturn(Observable.just(usersList));

    userDataRepository.users();

    verify(mockUserDataStoreFactory).createCloudDataStore();
    verify(mockUserDataStore).userEntityList();
  }

  @Test
  public void testGetUserHappyCase() {
    User userEntity = new User();
    given(mockUserDataStore.userEntityDetails(FAKE_USER_ID)).willReturn(Observable.just(userEntity));
    userDataRepository.user(FAKE_USER_ID);

    verify(mockUserDataStoreFactory).create(FAKE_USER_ID);
    verify(mockUserDataStore).userEntityDetails(FAKE_USER_ID);
  }
}
