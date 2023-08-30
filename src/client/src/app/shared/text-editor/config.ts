export interface PostConfig {
  base_url: string;
  suffix: string;
  selector?: string;
  height?: number;
  menubar?: boolean;
  language?: string;
  language_url?: string;
  plugins?: string[];
  toolbar?: string;
  promotion: boolean;
  branding: boolean;
}

export const defaultPostConfig: PostConfig = {
  base_url: '/tinymce',
  suffix: '.min',
  height: 500,
  menubar: true,
  language: 'bg_BG',
  language_url: 'assets/langs/bg_BG.js',
  toolbar:
    'undo redo | formatselect | bold italic backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
  promotion: false,
  branding: false,
  plugins: ['anchor', 'link', 'lists', 'image',]
};
