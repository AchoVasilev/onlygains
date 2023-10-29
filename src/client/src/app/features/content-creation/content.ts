import {
  exerciseTemplateStyling,
  threeImageTemplateStyling,
} from 'app/shared/models/text-editor/template-stylings';
import {
  exerciseTemplate,
  threeImageTemplate,
} from 'app/shared/models/text-editor/templates';

export const ContentResolver: Content = {
  exercise: {
    template: exerciseTemplate,
    styling: exerciseTemplateStyling,
  },
  post: {
    template: threeImageTemplate,
    styling: threeImageTemplateStyling,
  },
};

export interface Content {
  [key: string]: {
    template: string;
    styling: string;
  };
}
