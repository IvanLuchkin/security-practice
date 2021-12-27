import { AuthStorage } from './authStorage';

export const addAddress = async (address: string): Promise<any> => {
  const queryParameters: any = {};

  const headerParameters: any = {};

  if (
    AuthStorage.getUsername() !== undefined ||
    AuthStorage.getPassword() !== undefined
  ) {
    headerParameters['Authorization'] =
      'Basic ' +
      btoa(AuthStorage.getUsername() + ':' + AuthStorage.getPassword());
    headerParameters['Access-Control-Allow-Origin'] = '*';
    headerParameters['Content-Type'] = 'application/json';
  }
  return await request({
    path: `/address`,
    method: 'POST',
    headers: headerParameters,
    query: queryParameters,
    body: {
      country: address,
    },
  });
};

export const login = async (): Promise<any> => {
  const queryParameters: any = {};

  const headerParameters: any = {};

  if (
    AuthStorage.getUsername() !== undefined ||
    AuthStorage.getPassword() !== undefined
  ) {
    headerParameters['Authorization'] =
      'Basic ' +
      btoa(AuthStorage.getUsername() + ':' + AuthStorage.getPassword());
    headerParameters['Access-Control-Allow-Origin'] = '*';
  }
  return await request({
    path: `/login`,
    method: 'GET',
    headers: headerParameters,
    query: queryParameters,
  });
};

export const apiRegister = async (
  username: string,
  password: string
): Promise<any> => {
  const queryParameters: any = {};

  const headerParameters: any = {};
  headerParameters['Content-Type'] = 'application/json';
  headerParameters['Access-Control-Allow-Origin'] = '*';

  return await request({
    path: `/register`,
    method: 'POST',
    headers: headerParameters,
    query: queryParameters,
    body: {
      username: username,
      password: password,
    },
  });
};

export const request = async (context: any): Promise<Response> => {
  const { url, init } = createFetchParams(context);
  const response = await window.fetch.bind(window)(url, init);
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  throw response;
};

const createFetchParams = (context: any) => {
  const url = 'http://localhost:8080' + context.path;

  const body =
    context.body instanceof FormData || context.body instanceof URLSearchParams
      ? context.body
      : JSON.stringify(context.body);

  const init = {
    method: context.method,
    headers: context.headers,
    body,
  };
  return { url, init };
};
