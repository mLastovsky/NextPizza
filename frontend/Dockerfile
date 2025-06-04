# Стадия установки зависимостей
FROM node:18-alpine AS dependencies
WORKDIR /frontend
COPY package.json package-lock.json ./
RUN npm ci --frozen-lockfile

# Стадия сборки
FROM node:18-alpine AS builder
WORKDIR /frontend
COPY --from=dependencies /frontend/node_modules ./node_modules
COPY . .
RUN npm run build

# Финальная стадия для dev
FROM node:18-alpine AS runner
WORKDIR /frontend
ENV NODE_ENV=development

COPY --from=builder /frontend ./

EXPOSE 3000
CMD ["npm", "run", "dev"]
