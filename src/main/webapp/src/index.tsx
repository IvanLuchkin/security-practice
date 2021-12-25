import React from 'react';
import ReactDOM from 'react-dom';
import { App } from './App';
import { createMuiTheme, ThemeProvider } from '@material-ui/core';
import { Provider as AlertProvider } from 'react-alert';
import { alertOptions, AlertTemplate } from './AlertTemplate';

const theme = createMuiTheme({
  palette: {
    primary: {
      main: '#8275A9',
    },
  },
});

ReactDOM.render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <AlertProvider template={AlertTemplate} {...alertOptions}>
        <App />
      </AlertProvider>
    </ThemeProvider>
  </React.StrictMode>,
  document.getElementById('root')
);
