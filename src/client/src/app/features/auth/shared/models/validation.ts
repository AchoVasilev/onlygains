export const VALIDATION_ERRORS = new Map<string, string[]>([
  ['email', ['Въведете валиден и-мейл!']],
  ['required', ['Полето е задължително']],
  [
    //todo: map each error to a part of the regex
    'pattern',
    [
      'Полето трябва да съдържа главна буква',
      'Полето трябва да съдържа малка буква',
      'Полето трябва да съдържа число',
      'Полето трябва да съдържа специален символ',
      'Полето трябва да е дълго поне 8 букви',
    ],
  ],
]);

export const HTTP_ERRORS = new Map<string, string[]>([
  ['user.errors', ['User does not exist', 'User exists']],
  ['user.errors.status', ['User is deactivated']],
  [
    'user.errors.auth',
    ['Cannot authenticate user', 'Credentials do not match'],
  ],
]);

export const PASSWORDS_REGEX =
  '^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=\\D*\\d).{8,}$';
