import React from 'react';
import {
  Box,
  Button,
  CircularProgress,
  Grid,
  TextField,
  Typography,
} from '@material-ui/core';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { Link, useHistory } from 'react-router-dom';
import { AuthStorage } from './authStorage';
import { apiRegister } from './api';
import { useAlert } from 'react-alert';

const schema = yup.object().shape({
  username: yup.string().required('username is required'),
  password: yup
    .string()
    .ensure()
    .required('Password is required')
    .matches(
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{10,20})/,
      'Must Contain 10 to 20 Characters, One Uppercase, One Lowercase, One Number and One Special Case Character'
    ),
});

export const Register: React.FC = () => {
  const history = useHistory();
  const alert = useAlert();

  if (AuthStorage.isLoggedIn()) history.push('/home');

  const { register, handleSubmit, errors, formState } = useForm({
    resolver: yupResolver(schema),
  });

  const onSubmit = async (data: { username: any; password: any }) => {
    AuthStorage.storeCredentials(data.username, data.password);
    try {
      await apiRegister(data.username, data.password);
    } catch (e) {
      AuthStorage.clear();
      alert.show(e);
    }
  };

  return (
    <Grid container>
      <Grid
        container
        item
        xs={12}
        sm={6}
        alignItems='center'
        direction='column'
        justify='center'
      >
        <Grid
          container
          item
          xs={12}
          sm={6}
          alignItems='center'
          direction='column'
          justify='center'
        >
          <Box pb={3}>
            <Typography>Create an account</Typography>
          </Box>
          <form onSubmit={handleSubmit(onSubmit)}>
            <Grid
              container
              direction='row'
              justify='center'
              alignItems='center'
            >
              <TextField
                error={!!errors.username}
                helperText={errors.username?.message}
                label='Username'
                variant='outlined'
                fullWidth
                name='username'
                inputRef={register}
              />
            </Grid>
            <Box pt={3}>
              <Grid
                container
                direction='row'
                justify='center'
                alignItems='center'
              >
                <TextField
                  inputProps={{ 'data-testid': 'password' }}
                  error={!!errors.password}
                  helperText={errors.password?.message}
                  type='password'
                  autoComplete='current-password'
                  label='Password'
                  variant='outlined'
                  fullWidth
                  name='password'
                  inputRef={register}
                />
              </Grid>
            </Box>
            <Box pt={10}>
              <Grid
                container
                direction='row'
                justify='center'
                alignItems='center'
              >
                <Button
                  type='submit'
                  color='primary'
                  variant='contained'
                  className='primary-button'
                  disabled={formState.isSubmitting}
                >
                  SIGN UP{' '}
                  {formState.isSubmitting && (
                    <Box ml={2}>
                      <CircularProgress
                        title='loading'
                        color='primary'
                        size={20}
                      />
                    </Box>
                  )}
                </Button>
              </Grid>
            </Box>
            <Box pt={1}>
              <Grid
                container
                direction='row'
                justify='center'
                alignItems='center'
              >
                <Link to='/login'>
                  <span className='sign-up'>
                    Have an account?
                    <span className='button'> Sign in</span>
                  </span>
                </Link>
              </Grid>
            </Box>
          </form>
        </Grid>
      </Grid>
    </Grid>
  );
};
