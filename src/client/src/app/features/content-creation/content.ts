import {
  exerciseTemplateStyling,
  threeImageTemplateStyling,
} from 'app/shared/models/text-editor/template-stylings';
import {
  exerciseTemplate,
  threeImageTemplate,
} from 'app/shared/models/text-editor/templates';

export const ContentResolver: ContentTypeResolver = {
  exercise: {
    template: exerciseTemplate,
    styling: exerciseTemplateStyling,
  },
  post: {
    template: threeImageTemplate,
    styling: threeImageTemplateStyling,
  },
};

export interface ContentTypeResolver {
  [key: string]: Content;
}

export interface Content {
  template: string;
  styling: string;
}
