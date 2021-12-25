import React from 'react';
import { Redirect, Route, RouteProps } from 'react-router';
import { AuthStorage } from './authStorage';

export const PrivateRoute: React.FC<RouteProps> = ({
  component: Component,
  ...rest
}: RouteProps) => (
  <Route
    {...rest}
    render={(props) =>
      AuthStorage.isLoggedIn() ? (
        <Route {...props} component={Component} />
      ) : (
        <Redirect
          to={{ pathname: '/login', state: { from: props.location } }}
        />
      )
    }
  />
);
