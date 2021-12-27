import {
  Box,
  Button,
  CircularProgress,
  Grid,
  TextField,
  Typography,
} from '@material-ui/core';
import React from 'react';
import { AuthStorage } from './authStorage';
import { useHistory } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { addAddress } from './api';

const onSubmit = async (data: any) => {
  await addAddress(data.country);
};

export const Home: React.FC = () => {
  const { register, handleSubmit, formState } = useForm();
  const history = useHistory();
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
            <Typography>Fill your address</Typography>
          </Box>
          <form onSubmit={handleSubmit(onSubmit)}>
            <Grid
              container
              direction='row'
              justify='center'
              alignItems='center'
            >
              <TextField
                label='Country'
                variant='outlined'
                fullWidth
                name='country'
                inputRef={register}
              />
            </Grid>
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
                  SUBMIT
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
          </form>
        </Grid>
        <Button
          onClick={() => {
            history.push('/login');
            AuthStorage.clear();
          }}
        >
          Logout
        </Button>
      </Grid>
    </Grid>
  );
};
