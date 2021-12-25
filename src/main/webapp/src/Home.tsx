import { Button } from '@material-ui/core';
import React from 'react';
import { AuthStorage } from './authStorage';
import { useHistory } from 'react-router-dom';

export const Home: React.FC = () => {
  const history = useHistory();
  return (
    <Button
      onClick={() => {
        history.push('/login');
        AuthStorage.clear();
      }}
    >
      Logout
    </Button>
  );
};
