import React from 'react';
import { createBrowserHistory } from 'history';
import { Redirect, Route, Router, Switch } from 'react-router';
import { Login } from './Login';
import { PrivateRoute } from './PrivateRoute';
import { Home } from './Home';
import { Register } from './Register';

export const AppRouter: React.FC = () => {
  const history = createBrowserHistory();

  return (
    <Router history={history}>
      <Switch>
        <Route path='/login' component={Login} />
        <Route path='/register' component={Register} />
        <PrivateRoute path='/home' component={Home} />
        <Redirect from='*' to='/login' />
      </Switch>
    </Router>
  );
};
