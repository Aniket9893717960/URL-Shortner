import autoprefixer from 'autoprefixer';
import tailwindcssPlugin from '@tailwindcss/postcss'; // Import the new plugin

export default {
  plugins: [
    tailwindcssPlugin, // Use @tailwindcss/postcss here
    autoprefixer,
  ],
};