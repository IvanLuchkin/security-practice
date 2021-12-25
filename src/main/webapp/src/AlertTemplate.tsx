import React from 'react';
import CheckIcon from '@material-ui/icons/Check';
import ErrorOutlineIcon from '@material-ui/icons/ErrorOutline';
import CloseIcon from '@material-ui/icons/Close';
import {
  AlertComponentPropsWithStyle,
  positions,
  transitions,
} from 'react-alert';

const alertStyle: React.CSSProperties = {
  backgroundColor: '#2D353E',
  color: 'white',
  padding: '10px',
  textTransform: 'none',
  borderRadius: '3px',
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  boxShadow: '0px 2px 2px 2px rgba(0, 0, 0, 0.03)',
  fontFamily: 'Arial',
  width: '30vw',
  boxSizing: 'border-box',
};

const buttonStyle = {
  marginLeft: '20px',
  border: 'none',
  backgroundColor: 'transparent',
  cursor: 'pointer',
  color: '#FFFFFF',
};

export const alertOptions = {
  position: positions.BOTTOM_CENTER,
  timeout: 8000,
  offset: '1vh',
  transition: transitions.FADE,
};

export const AlertTemplate: React.FC<AlertComponentPropsWithStyle> = ({
  message,
  options,
  style,
  close,
}: AlertComponentPropsWithStyle) => {
  return (
    <div style={{ ...alertStyle, ...style }}>
      {options.type === 'success' && (
        <CheckIcon style={{ color: 'green', marginRight: '5px' }} />
      )}
      {options.type === 'error' && (
        <ErrorOutlineIcon style={{ color: 'red', marginRight: '5px' }} />
      )}
      <span style={{ flex: 2 }}>{message}</span>
      <button onClick={close} style={buttonStyle}>
        <CloseIcon />
      </button>
    </div>
  );
};
