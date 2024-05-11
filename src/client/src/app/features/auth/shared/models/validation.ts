export const VALIDATION_ERRORS = new Map<string, string>([
  ['email', 'Въведете валиден и-мейл!'],
  ['required', 'Полето е задължително'],
]);

export const HTTP_ERRORS = new Map<string, string[]>([
  ['user.errors', ['User does not exist', 'User exists']],
  ['user.errors.status', ['User is deactivated']],
  [
    'user.errors.auth',
    ['Cannot authenticate user', 'Credentials do not match'],
  ],
]);
